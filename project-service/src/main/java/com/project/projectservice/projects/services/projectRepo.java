package com.project.projectservice.projects.services;

import com.project.projectservice.projects.data.DTO.filterDTO;
import com.project.projectservice.projects.data.DTO.fullProjectDTO;
import com.project.projectservice.projects.data.DTO.projectDTOImages;
import com.project.projectservice.projects.data.projects;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface projectRepo extends MongoRepository<projects, String> {

    @Aggregation(pipeline = {
            "{ $match: { _id: ?0 } }",
            "{ $addFields: { imageMain: { $toObjectId: '$imageMain' }, imageExterior: { $toObjectId: '$imageExterior' } } }",
            "{ $lookup: { from: 'photo', localField: 'imageMain', foreignField: '_id', as: 'imageMain' } }",
            "{ $lookup: { from: 'photo', localField: 'imageExterior', foreignField: '_id', as: 'imageExterior' } }",
            "{ $set: { imageMain: { $arrayElemAt: ['$imageMain', 0] }, imageExterior: { $arrayElemAt: ['$imageExterior', 0] } } }",
            "{ $unwind: { path: '$exteriorPhoto', preserveNullAndEmptyArrays: true } }",
            "{ $addFields: { exteriorPhoto: { $toObjectId: '$exteriorPhoto' } } }",
            "{ $lookup: { from: 'photo', localField: 'exteriorPhoto', foreignField: '_id', as: 'exteriorPhoto' } }",
            "{ $addFields: { exteriorPhoto: { $arrayElemAt: ['$exteriorPhoto', 0] } } }",
            "{ $group: { _id: '$_id', name: { $first: '$name' }, coordinates: { $first: '$coordinates' }, location: { $first: '$location' }, ownLocation: { $first: '$ownLocation' }, description: { $first: '$description' }, developer: { $first: '$developer' }, priceFrom: { $first: '$priceFrom' }, sizeFrom: { $first: '$sizeFrom' }, bedrooms: { $first: '$bedrooms' }, amenities: { $first: '$amenities' }, VrUrl: { $first: '$VrUrl' }, Url3D: { $first: '$Url3D' }, imageMain: { $first: '$imageMain' }, imageExterior: { $first: '$imageExterior' }, company: { $first: '$company' }, handover: { $first: '$handover' }, propertyType: { $first: '$propertyType' }, trakheesi: { $first: '$trakheesi' }, exteriorPhoto: { $push: '$exteriorPhoto' }, interiorPhoto: { $first: '$interiorPhoto' }, completionStatus: { $first: '$completionStatus' }, floorPlans: { $first: '$floorPlans' }, paymentPlan: { $first: '$paymentPlan' } } }",
            "{ $unwind: { path: '$interiorPhoto', preserveNullAndEmptyArrays: true } }",
            "{ $addFields: { interiorPhoto: { $toObjectId: '$interiorPhoto' } } }",
            "{ $lookup: { from: 'photo', localField: 'interiorPhoto', foreignField: '_id', as: 'interiorPhoto' } }",
            "{ $addFields: { interiorPhoto: { $arrayElemAt: ['$interiorPhoto', 0] } } }",
            "{$group: { _id: \"$_id\", name: { $first: \"$name\", }, coordinates: { $first: \"$coordinates\", }, location: { $first: \"$location\", }, ownLocation: { $first: \"$ownLocation\", }, description: { $first: \"$description\", }, developer: { $first: \"$developer\", }, priceFrom: { $first: \"$priceFrom\", }, sizeFrom: { $first: \"$sizeFrom\", }, bedrooms: { $first: \"$bedrooms\", }, amenities: { $first: \"$amenities\", }, VrUrl: { $first: \"$VrUrl\", }, Url3D: { $first: \"$Url3D\", }, imageMain: { $first: \"$imageMain\", }, imageExterior: { $first: \"$imageExterior\", }, company: { $first: \"$company\", }, handover: { $first: \"$handover\", }, propertyType: { $first: \"$propertyType\", }, trakheesi: { $first: \"$trakheesi\", }, interiorPhoto: { $push: \"$interiorPhoto\", }, exteriorPhoto: { $first: \"$exteriorPhoto\", }, completionStatus: { $first: \"$completionStatus\", }, floorPlans: { $first: \"$floorPlans\", }, paymentPlan: { $first: \"$paymentPlan\", } }}",
            "{$project: { _id: \"$_id\", name: \"$name\", coordinates: \"$coordinates\", location: \"$location\", ownLocation: \"$ownLocation\", description: \"$description\", developer: \"$developer\", priceFrom: \"$priceFrom\", sizeFrom: \"$sizeFrom\", bedrooms: \"$bedrooms\", amenities: \"$amenities\", VrUrl: \"$VrUrl\", Url3D: \"$Url3D\", imageMain: \"$imageMain\", imageExterior: \"$imageExterior\", company: \"$company\", handover: \"$handover\", propertyType: \"$propertyType\", trakheesi: \"$trakheesi\", interiorPhoto: \"$interiorPhoto\", exteriorPhoto: \"$exteriorPhoto\", completionStatus: \"$completionStatus\", paymentPlan: \"$paymentPlan\", floorPlans: { $objectToArray: \"$floorPlans\", }, }}",
            "{$unwind: { path: \"$floorPlans\" }}",
            "{$addFields: { floorPlanKey: \"$floorPlans.k\", floorPlanValue: \"$floorPlans.v\", }}",
            "{$unwind: { path: \"$floorPlanValue\" }}",
            "{$project: { floorPlans: 0, }}",
            "{$set: { \"floorPlanValue.imageUrl\": { $toObjectId: \"$floorPlanValue.imageUrl\", }, }}",
            "{$lookup: { from: \"photo\", localField: \"floorPlanValue.imageUrl\", foreignField: \"_id\", as: \"floorPlanValue.imageUrl\", }}",
            "{$addFields: { \"floorPlanValue.imageUrl\": { $arrayElemAt: [\"$floorPlanValue.imageUrl\", 0], }, }}",
            "{$group: { _id: \"$_id\", name: { $first: \"$name\", }, coordinates: { $first: \"$coordinates\", }, location: { $first: \"$location\", }, ownLocation: { $first: \"$ownLocation\", }, description: { $first: \"$description\", }, developer: { $first: \"$developer\", }, priceFrom: { $first: \"$priceFrom\", }, sizeFrom: { $first: \"$sizeFrom\", }, bedrooms: { $first: \"$bedrooms\", }, amenities: { $first: \"$amenities\", }, VrUrl: { $first: \"$VrUrl\", }, Url3D: { $first: \"$Url3D\", }, imageMain: { $first: \"$imageMain\", }, imageExterior: { $first: \"$imageExterior\", }, company: { $first: \"$company\", }, handover: { $first: \"$handover\", }, propertyType: { $first: \"$propertyType\", }, trakheesi: { $first: \"$trakheesi\", }, interiorPhoto: { $first: \"$interiorPhoto\", }, exteriorPhoto: { $first: \"$exteriorPhoto\", }, completionStatus: { $first: \"$completionStatus\", }, paymentPlan: { $first: \"$paymentPlan\", }, floorPlans: { $push: { floorPlanKey: \"$floorPlanKey\", floorPlanValue: \"$floorPlanValue\", }, }, }}",
            "{$unwind: { path: \"$floorPlans\" }}",
            "{$group: { _id: { _id: \"$_id\", floorPlanKey: \"$floorPlans.floorPlanKey\", }, name: { $first: \"$name\", }, coordinates: { $first: \"$coordinates\", }, location: { $first: \"$location\", }, ownLocation: { $first: \"$ownLocation\", }, description: { $first: \"$description\", }, developer: { $first: \"$developer\", }, priceFrom: { $first: \"$priceFrom\", }, sizeFrom: { $first: \"$sizeFrom\", }, bedrooms: { $first: \"$bedrooms\", }, amenities: { $first: \"$amenities\", }, VrUrl: { $first: \"$VrUrl\", }, Url3D: { $first: \"$Url3D\", }, imageMain: { $first: \"$imageMain\", }, imageExterior: { $first: \"$imageExterior\", }, company: { $first: \"$company\", }, handover: { $first: \"$handover\", }, propertyType: { $first: \"$propertyType\", }, trakheesi: { $first: \"$trakheesi\", }, interiorPhoto: { $first: \"$interiorPhoto\", }, exteriorPhoto: { $first: \"$exteriorPhoto\", }, completionStatus: { $first: \"$completionStatus\", }, paymentPlan: { $first: \"$paymentPlan\", }, floorPlanValues: { $push: \"$floorPlans.floorPlanValue\", }, }}",
            "{$group: { _id: \"$_id._id\", name: { $first: \"$name\", }, coordinates: { $first: \"$coordinates\", }, location: { $first: \"$location\", }, ownLocation: { $first: \"$ownLocation\", }, description: { $first: \"$description\", }, developer: { $first: \"$developer\", }, priceFrom: { $first: \"$priceFrom\", }, sizeFrom: { $first: \"$sizeFrom\", }, bedrooms: { $first: \"$bedrooms\", }, amenities: { $first: \"$amenities\", }, VrUrl: { $first: \"$VrUrl\", }, Url3D: { $first: \"$Url3D\", }, imageMain: { $first: \"$imageMain\", }, imageExterior: { $first: \"$imageExterior\", }, company: { $first: \"$company\", }, handover: { $first: \"$handover\", }, propertyType: { $first: \"$propertyType\", }, trakheesi: { $first: \"$trakheesi\", }, interiorPhoto: { $first: \"$interiorPhoto\", }, exteriorPhoto: { $first: \"$exteriorPhoto\", }, completionStatus: { $first: \"$completionStatus\", }, paymentPlan: { $first: \"$paymentPlan\", }, floorPlan: { $push: { k: \"$_id.floorPlanKey\", v: \"$floorPlanValues\", }, }, }}",
            "{$set: { floorPlan: { $arrayToObject: \"$floorPlan\" } }}"

    })
    fullProjectDTO customAggregation(String projectId);


    @Aggregation(pipeline = {
            "{$match:{ company:?0, name:{$regex:?1,$options:'i'}, priceFrom: { $gte: ?2, $lte: ?3 }, sizeFrom: { $gte: ?4, $lte:?5 }, bedrooms: { $in: ?6 }, propertyType: { $in: ?7 }} }",
            "{$project: {name:1,coordinates:1,developer:1,priceFrom:1,location:1,ownLocation:1,handover:1,imageMain:1,company:1}}",
            "{$set: { price: \"$priceFrom\" }}",
            "{$set: { image: \"$imageMain\" }}",
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
    List<projectDTOImages> getAllProjects(
            String company,
            String name,
            Integer priceFrom,
            Integer priceTo,
            Integer sizeFrom,
            Integer sizeTo,
            List<String> bedrooms,
            List<String> propertyType
    );

}
