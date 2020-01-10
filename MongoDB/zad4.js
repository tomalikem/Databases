function change_user_name(user_id, name)
{
    db.user.update({user_id: user_id},{$set: {name: name}})
}

change_user_name("5Xh4Qc3rxhAQ_NcNtxLssQ", "Mikolaj")