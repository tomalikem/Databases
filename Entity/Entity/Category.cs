using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EntityFramework
{
    class Category
    {
        public int CategoryId { get;}
        public String Name { get; set; }

        public String Description { get; set; }
        public List<Product> Products { get; set; }
    }
}
