import com.ariadne.auditlog.AuditLogListener
import com.ariadne.auditlog.AuditLogListenerUtil
import com.ariadne.domain.AuditLogEvent

// Place your Spring DSL code here
beans = {
    auditLogListener(AuditLogListener, ref('hibernateDatastore'))  {
        grailsApplication = ref('grailsApplication')
        verbose = grailsApplication.config.auditLog.verbose ?: false
        transactional = grailsApplication.config.auditLog.transactional ?: false
        sessionAttribute = grailsApplication.config.auditLog.sessionAttribute ?: ""
        actorKey = grailsApplication.config.auditLog.actorKey ?: ""
        truncateLength = grailsApplication.config.auditLog.truncateLength ?: AuditLogEvent.MAX_SIZE
        actorClosure = grailsApplication.config.auditLog.actorClosure ?: AuditLogListenerUtil.actorDefaultGetter
        defaultIgnoreList = grailsApplication.config.auditLog.defaultIgnore?.asImmutable() ?: ['version', 'lastUpdated'].asImmutable()
        defaultMaskList = grailsApplication.config.auditLog.defaultMask?.asImmutable() ?: ['password'].asImmutable()
        propertyMask = grailsApplication.config.auditLog.propertyMask ?: "**********"
        replacementPatterns = grailsApplication.config.auditLog.replacementPatterns
        logIds = grailsApplication.config.auditLog.logIds ?: false
    }
}