package com.project.amenitiesservice.amenities.services;

import com.project.amenitiesservice.amenities.data.amenities;
import com.project.amenitiesservice.amenities.data.amenitiesImages;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface amenitiesRepo extends MongoRepository<amenities,String> {

    @Aggregation(pipeline = {
            "{$match: { \"name\": { \"$in\": ?0 } }}",
            "{$set: { \"image\": { $toObjectId: \"$image\", } }}",
            "{$lookup: { from: \"photo\", localField: \"image\", foreignField: \"_id\", as: \"image\" }}",
            "{$set: { image: { $arrayElemAt: [\"$image\", 0], }, }}"
    })
    List<amenitiesImages> getAmenitiesWithImages(List<String> amenities);

}
