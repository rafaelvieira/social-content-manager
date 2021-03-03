package com.rafalabs.socialcm.content.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.rafalabs.socialcm.content.repository.entity.ContentEntity

@Repository
interface ContentRepository: CrudRepository<ContentEntity, Long> {

}
