package lee.bowon.holiday.repository;

import lee.bowon.holiday.entity.LastUpdateDateInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LastUpdateDateInfoRepository : JpaRepository<LastUpdateDateInfo, Long>,
    JpaSpecificationExecutor<LastUpdateDateInfo> {

    fun findByTypeName(typeName: String): Optional<LastUpdateDateInfo>

}