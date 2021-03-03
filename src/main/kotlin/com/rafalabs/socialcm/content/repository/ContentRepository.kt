package unit.com.rafalabs.socialcm.content.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unit.com.rafalabs.socialcm.content.repository.entity.ContentEntity

@Repository
class ContentRepository: CrudRepository<ContentEntity> {

}
