var mapFunction = function()
{
    var key = this.business_id;
        var value ={sum: this.review_count,count: 1}
    emit(key, value);
};

var reduceFunction = function(key, values)
{
    var result ={sum: 0,count: 0};
    values.forEach(function(value)
        {result.sum += value.sum;result.count += value.count;})
    return result;
}

var finalizeFunction = function(key, reducedValue)
{
    var avgReviews = reducedValue.sum / reducedValue.count;
    return avgReviews;
}

db.buisiness.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: "reviews_per_business_avg",
        finalize: finalizeFunction,
    }
)
