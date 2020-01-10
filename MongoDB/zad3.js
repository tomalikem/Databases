function get_business_of_category(category)
{
    return db.buisiness.find({categories: {"$in" : [category]}})
}
var tips = get_business_of_category("Doctors")
tips.forEach(function(doc){print(doc)}
)