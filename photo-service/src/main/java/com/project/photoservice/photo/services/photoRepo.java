package com.project.photoservice.photo.services;

import com.project.photoservice.photo.data.photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface photoRepo extends MongoRepository<photo,String> {
}
