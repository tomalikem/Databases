function insertReview(user_id, review_id, business_id, stars, text)
{
    db.review.insert(
    {
        user_id: user_id,
        review_id: review_id,
        business_id: business_id,
        stars: stars,
        text: text,
        votes:{funny: 0, useful: 0, cool: 0},
        date: new Date(),
        type: "review"
    })
}

insertReview("uid", "rid", "bid", 4, "Review text")