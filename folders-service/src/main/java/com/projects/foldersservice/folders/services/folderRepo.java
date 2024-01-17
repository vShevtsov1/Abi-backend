package com.projects.foldersservice.folders.services;

import com.projects.foldersservice.folders.data.DTO.folderContent;
import com.projects.foldersservice.folders.data.folder;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface folderRepo  extends MongoRepository<folder, String> {

    List<folder> getAllByOwner(String owner);


    @Aggregation({
            "{ $match: { _id: ?0 } }",
            "{ $unwind: { path: '$projects', preserveNullAndEmptyArrays: true } }",
            "{ $addFields: { projectId: { $toObjectId: '$projects' } } }",
            "{ $lookup: { from: 'projects-preview', localField: 'projectId', foreignField: '_id', as: 'projectPreview' } }",
            "{ $group: { _id: '$_id', owner: { $first: '$owner' }, name: { $first: '$name' }, projects: { $push: { $arrayElemAt: ['$projectPreview', 0] } } } }",
            "{ $unwind: { path: '$projects', preserveNullAndEmptyArrays: true } }",
            "{ $addFields: { imageId: { $toObjectId: '$projects.image' } } }",
            "{ $lookup: { from: 'photo', localField: 'imageId', foreignField: '_id', as: 'imagePreview' } }",
            "{ $set: { 'projects.image': '$imagePreview' } }",
            "{ $set: { 'projects.image': { $arrayElemAt: ['$projects.image', 0] } } }",
            "{ $project: { imageId: 0, imagePreview: 0 } }",
            "{ $group: { _id: '$_id', owner: { $first: '$owner' }, name: { $first: '$name' }, projects: { $push: { $cond: { if: { $and: [ { $ne: ['$projects', null] }, { $ne: ['$projects', {}] } ] }, then: '$projects', else: '$$REMOVE' } } } } }"
    })
    folderContent getFolderContent(String folderId);

}
