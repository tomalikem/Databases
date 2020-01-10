using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EntityFramework
{
    class Customer
    {
        [Key]
        public String CompanyName { get; set; }
        public String Descrition { get; set; }
    }
}
