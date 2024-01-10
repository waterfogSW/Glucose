package com.waterfogsw.glucose.common.jwt.util

import com.waterfogsw.glucose.common.jwt.error.ExpiredJwtException
import com.waterfogsw.glucose.common.jwt.error.SignatureException
import com.waterfogsw.glucose.common.jwt.vo.JwtClaims
import com.waterfogsw.glucose.common.jwt.vo.getClaims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.*

class JwtTokenProviderTest : DescribeSpec({

    val secret = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabc"

    describe("createToken()") {
        context("secret key가 주어지지 않으면") {
            it("서명되지 않은 새로운 토큰을 발급한다.") {
                // arrange
                val claims = JwtClaims {
                    registeredClaims {
                        sub = "sub"
                        aud = setOf("aud1", "aud2")
                    }
                    customClaims {
                        this["key"] = "value"
                    }
                }

                // act
                val token: String = JwtTokenProvider.createToken(jwtClaims = claims)

                // assert
                val body = Jwts
                    .parser()
                    .unsecured()
                    .build()
                    .parse(token)
                    .getClaims()

                body.sub shouldBe "sub"
                body.aud shouldBe setOf("aud1", "aud2")
                body.customClaims["key"] shouldBe "value"
            }
        }

        context("secret key가 주어지면") {
            it("서명된 새로운 토큰을 발급한다.") {
                // arrange
                val claims = JwtClaims {
                    registeredClaims {
                        sub = "sub"
                        aud = setOf("aud1", "aud2")
                    }
                    customClaims {
                        this["key"] = "value"
                    }
                }

                // act
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = claims,
                    secret = secret,
                )

                // assert
                val key = Keys.hmacShaKeyFor(secret.toByteArray())
                val body = Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parse(token)
                    .getClaims()

                body.sub shouldBe "sub"
                body.aud shouldBe setOf("aud1", "aud2")
                body.customClaims["key"] shouldBe "value"
            }
        }

    }


    describe("validateToken(token: String, secret?: String)") {
        context("서명되지 않은 토큰과 secret이 null이면") {
            it("토큰의 유효성을 검증한다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                )

                // act
                val result = runCatching {
                    JwtTokenProvider.validateToken(token)
                }.exceptionOrNull()

                // assert
                result shouldBe null
            }
        }

        context("서명된 토큰과 secret이 null") {
            it("토큰의 서명을 제외한 유효성을 검증한다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                // act
                val result = runCatching {
                    JwtTokenProvider.validateToken(token)
                }.exceptionOrNull()

                // assert
                result shouldBe null
            }
        }


        context("서명된 토큰과 secret이 주어지면") {
            it("토큰의 서명과 유효성을 검증한다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                // act
                val result = runCatching {
                    JwtTokenProvider.validateToken(token, secret)
                }.exceptionOrNull()

                // assert
                result shouldBe null
            }
        }

        context("토큰의 기간이 만료되었으면") {
            it("ExpiredJwtException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                            exp = Date.from(Date().toInstant().minusSeconds(1))
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                // act
                val result = runCatching {
                    JwtTokenProvider.validateToken(token, secret)
                }.exceptionOrNull()

                // assert
                result.shouldBeInstanceOf<ExpiredJwtException>()
            }
        }

        context("토큰의 서명이 다른경우") {
            it("SignatureException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                val anotherKey = "testtestetesttestetesttestetesttestetesttestetesttestetestteste"

                // act
                val result = runCatching {
                    JwtTokenProvider.validateToken(token, anotherKey)
                }.exceptionOrNull()

                // assert
                result.shouldBeInstanceOf<SignatureException>()
            }
        }
    }

    describe("getClaims()") {
        context("토큰의 기간이 만료되지 않았으면") {
            it("토큰의 claims를 반환한다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                // act
                val claims = JwtTokenProvider.getClaims(token, secret)

                // assert
                claims.sub shouldBe "sub"
                claims.aud shouldBe setOf("aud1", "aud2")

                claims.customClaims["key"] shouldBe "value"
            }
        }

        context("토큰의 기간이 만료되었으면") {
            it("ExpiredJwtException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                            exp = Date.from(Date().toInstant().minusSeconds(1))
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                // act
                val result = runCatching {
                    JwtTokenProvider.getClaims(token, secret)
                }.exceptionOrNull()

                // assert
                result.shouldBeInstanceOf<ExpiredJwtException>()
            }
        }

        context("토큰의 서명이 다른경우") {
            it("SignatureException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = setOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                val anotherKey = "testtestetesttestetesttestetesttestetesttestetesttestetestteste"

                // act
                val result = runCatching {
                    JwtTokenProvider.getClaims(token, anotherKey)
                }.exceptionOrNull()

                // assert
                result.shouldBeInstanceOf<SignatureException>()
            }
        }
    }

})
