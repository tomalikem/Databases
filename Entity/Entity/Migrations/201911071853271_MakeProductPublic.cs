namespace Entity.Migrations
{
    using System;
    using System.Data.Entity.Migrations;

    public partial class MakeProductPublic : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Products", "ProdName", c => c.String());
            AddColumn("dbo.Products", "UnitsInStock", c => c.Int());
        }

        public override void Down()
        {
            DropColumn("dbo.Products", "ProdName");
            DropColumn("dbo.Products", "UnitsInStock");
        }
    }
}

