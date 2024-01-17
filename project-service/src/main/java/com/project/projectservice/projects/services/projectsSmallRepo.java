package com.project.projectservice.projects.services;

import com.project.projectservice.projects.data.DTO.projectDTOImages;
import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface projectsSmallRepo extends MongoRepository<projectsSmall,String> {
    List<projectsSmall> findAllByCompany(String companyId);

    @Aggregation(pipeline = {
            "{$match: {company: ?0}}",
            "{$lookup: { " +
                    "from: 'photo'," +
                    "let: { imageId: { $toObjectId: '$image' } }," +
                    "pipeline: [" +
                    "{ $match: { $expr: { $eq: [ '$_id', '$$imageId' ] } } }," +
                    "{ $project: { _id: 0, photo_url: 1, photo_preview: 1 } }" +
                    "]," +
                    "as: 'photo'" +
                    "}}",
            "{$project: {" +
                    "name: 1," +
                    "coordinates: 1," +
                    "developer: 1," +
                    "price: 1," +
                    "location: 1," +
                    "ownLocation: 1," +
                    "handover: 1," +
                    "image: 1," +
                    "company: 1," +
                    "photo: 1" +
                    "}}"
    })
    List<projectDTOImages> getAllProjects(String company);




}
