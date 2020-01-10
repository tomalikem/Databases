db.buisiness.createIndex({"city": 1})
db.buisiness.distinct('city').sort();

db.review.find({"date" : {"$gte" : "2011-01-01"}}).count();

db.buisiness.find
(
{'open': false},
{'name': 1,'full_address': 1,'stars': 1}
)


db.user.createIndex({"name": 1})
db.user.find({$or:[{'votes.funny': 0},{'votes.useful': 0},]})
.sort({'name': 1})

    
db.tip.aggregate
([
{$group: {_id: "$business_id", count: {$sum: 1}}},
{$lookup:{from: "buisiness", localField: "_id", foreignField: "business_id", as: "business_tip"}},
{$unwind: "$business_tip"},
{$project:{"name": "$business_tip.name","count": "$count"}},
{$sort: {tip: 1}}
])


db.review.aggregate
([
{$group:{_id: "$business_id", avgStars: {$avg: "$stars"}}},
{$lookup:{from: "buisiness",localField: "_id",foreignField: "business_id",as: "business_review"}},
{$unwind: "$business_review"},
{$project:{"name": "$business_review.name","avgStars": "$avgStars"}},
{ $match : { avgStars: { $gte: 4 } } } 
])

db.buisiness.deleteMany({"stars": 2.0})




