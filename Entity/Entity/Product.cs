using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations.Schema;

namespace EntityFramework
{
    [Table("Products", Schema = "dbo")]
    class Product
    {
        public int ProductID { get; set; }
        [Column("ProdName")]
        public string Name { get; set; }
        public int UnitsInStock { get; set; }
        public int CategoryID { get; set; }
        [Column("Money")]
        public decimal Unitprice { get; set; }
    }
}

