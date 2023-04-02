package lee.bowon.holiday.repository;

import lee.bowon.holiday.entity.LastUpdateDateInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface LastUpdateDateInfoRepository : JpaRepository<LastUpdateDateInfo, Long>,
    JpaSpecificationExecutor<LastUpdateDateInfo> {
}