namespace Entity.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddUnitPrice1 : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Products", "Money", c => c.Decimal());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Products", "Money");
        }
    }
}
