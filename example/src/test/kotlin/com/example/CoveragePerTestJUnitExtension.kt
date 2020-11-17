package com.example

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.io.File

class CoveragePerTestJUnitExtension : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext) {
        val testIdentifier = context.requiredTestMethod.run {
            // TODO: use more appropriate (unsupported in method names) separator
            "${declaringClass.typeName}##$name"
        }

        JacocoAgent.setSessionId(testIdentifier)
    }

    override fun afterEach(context: ExtensionContext) {
        File("build/coveragepertest").apply { if (!exists()) { mkdirs() } }

        val sessionIdBefore = JacocoAgent.getSessionId()
        val executionData = JacocoAgent.getExecutionData(reset = true)

        File("build/coveragepertest/test-$sessionIdBefore.exec").writeBytes(executionData)
    }

    private object JacocoAgent {
        private val agent = Class.forName("org.jacoco.agent.rt.RT")
            .getMethod("getAgent")
            .invoke(null)

        /**
         * See [org.jacoco.agent.rt.IAgent.getSessionId].
         */
        fun getSessionId() =
            agent.javaClass.getMethod("getSessionId").invoke(agent) as String

        /**
         * See [org.jacoco.agent.rt.IAgent.setSessionId].
         */
        fun setSessionId(id: String) {
            agent.javaClass.getMethod("setSessionId", String::class.java).invoke(agent, id)
        }

        /**
         * See [org.jacoco.agent.rt.IAgent.getExecutionData].
         */
        fun getExecutionData(reset: Boolean) =
            agent.javaClass.getMethod("getExecutionData", Boolean::class.java).invoke(agent, reset) as ByteArray
    }
}
