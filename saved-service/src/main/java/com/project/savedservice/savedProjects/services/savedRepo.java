package com.project.savedservice.savedProjects.services;

import com.project.savedservice.savedProjects.data.DTO.userSavedProjectsDTO;
import com.project.savedservice.savedProjects.data.savedProjects;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface savedRepo extends MongoRepository<savedProjects, String> {

    savedProjects findByUserId(String userId);

    @Aggregation(pipeline = {
            "{$match: {userId:?0}}",
            "{$unwind: { path: \"$savedProjects\", preserveNullAndEmptyArrays: true }}",
            "{$set: { savedProjects: { $toObjectId:\"$savedProjects\" } }}",
            "{$lookup: { from: \"projects-preview\", localField: \"savedProjects\", foreignField: \"_id\", as: \"savedProjects\" }}",
            "{$set: { savedProjects: { $arrayElemAt: ['$savedProjects', 0] } }}",
            "{$set: { \"savedProjects.image\": { $toObjectId:\"$savedProjects.image\" } }}",
            "{$lookup: { from: \"photo\", localField: \"savedProjects.image\", foreignField: \"_id\", as: \"savedProjects.image\" }}",
            "{$set: { \"savedProjects.image\": { $arrayElemAt: ['$savedProjects.image', 0] } }}",
            "{$group: { _id: \"$_id\", userId: {$first: \"$userId\"}, savedProjects: { $push: { $cond: { if: { $and: [ { $ne: ['$savedProjects', null] }, { $ne: ['$savedProjects', {}] } ]}, then: '$savedProjects', else: '$$REMOVE'}}} }}",
        "{$project: { _id: 1, userId: 1, savedProjects: { $ifNull: ['$savedProjects', []] } }}"
        })
        userSavedProjectsDTO getUserSavedProjects(String userId);



}
