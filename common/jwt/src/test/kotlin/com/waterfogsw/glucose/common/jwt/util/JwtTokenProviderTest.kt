package com.waterfogsw.glucose.common.jwt.util

import com.auth0.jwt.JWT
import com.waterfogsw.glucose.common.jwt.error.JwtExpiredException
import com.waterfogsw.glucose.common.jwt.error.JwtVerificationException
import com.waterfogsw.glucose.common.jwt.vo.JwtClaims
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
                        aud = listOf("aud1", "aud2")
                    }
                    customClaims {
                        this["key"] = "value"
                        this["list"] = listOf("a", "b", "c")
                    }
                }

                // act
                val token: String = JwtTokenProvider.createToken(jwtClaims = claims)

                // assert
                val body = JWT.decode(token)
                val jwtClaims = JwtClaims.from(body)

                jwtClaims.sub shouldBe "sub"
                jwtClaims.aud shouldBe setOf("aud1", "aud2")
                jwtClaims.customClaims["key"] shouldBe "value"
                jwtClaims.customClaims["list"] shouldBe listOf("a", "b", "c")
            }
        }

        context("secret key가 주어지면") {
            it("서명된 새로운 토큰을 발급한다.") {
                // arrange
                val claims = JwtClaims {
                    registeredClaims {
                        sub = "sub"
                        aud = listOf("aud1", "aud2")
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
                val body = JWT.decode(token)
                val jwtClaims = JwtClaims.from(body)

                jwtClaims.sub shouldBe "sub"
                jwtClaims.aud shouldBe setOf("aud1", "aud2")
                jwtClaims.customClaims["key"] shouldBe "value"
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
                            aud = listOf("aud1", "aud2")
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

        context("서명된 토큰과 null인 secret이 주어지면") {
            it("토큰의 서명을 제외한 유효성을 검증한다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = listOf("aud1", "aud2")
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
                println(result)
            }
        }


        context("서명된 토큰과 secret이 주어지면") {
            it("토큰의 서명과 유효성을 검증한다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = listOf("aud1", "aud2")
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

        context("서명된 토큰과 서명과 불일치하는 secret이 주어지면") {
            it("JwtVerificationException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = listOf("aud1", "aud2")
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
                result.shouldBeInstanceOf<JwtVerificationException>()
            }
        }

        context("토큰의 기간이 만료되었으면") {
            it("ExpiredJwtException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = listOf("aud1", "aud2")
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
                result.shouldBeInstanceOf<JwtExpiredException>()
            }
        }

        context("토큰의 서명이 다른경우") {
            it("SignatureException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = listOf("aud1", "aud2")
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
                result.shouldBeInstanceOf<JwtVerificationException>()
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
                            aud = listOf("aud1", "aud2")
                        }
                        customClaims {
                            this["key"] = "value"
                        }
                    },
                    secret = secret,
                )

                // act
                val claims = JwtTokenProvider.getClaims(token, secret).getOrNull()!!

                // assert
                claims.sub shouldBe "sub"
                claims.aud shouldBe listOf("aud1", "aud2")

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
                            aud = listOf("aud1", "aud2")
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
                result.shouldBeInstanceOf<JwtExpiredException>()
            }
        }

        context("토큰의 서명이 다른경우") {
            it("SignatureException을 발생시킨다.") {
                // arrange
                val token: String = JwtTokenProvider.createToken(
                    jwtClaims = JwtClaims {
                        registeredClaims {
                            sub = "sub"
                            aud = listOf("aud1", "aud2")
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
                result.shouldBeInstanceOf<JwtVerificationException>()
            }
        }
    }

})
