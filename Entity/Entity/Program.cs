using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Contexts;
using System.Text;
using System.Threading.Tasks;
using Entity;
using Entity.Migrations;

namespace EntityFramework
{
    class Program
    {
        static void Main(string[] args)
        {
//            Console.WriteLine("Write category name");
//            String CategoryName = Console.ReadLine();
//            Category category = new Category();
//            category.Name = CategoryName;


//            ProdContext context = new ProdContext();

//            context.Categories.Add(category);

//            context.SaveChanges();

//            IQueryable<string> query = from b in context.Categories
//                                       orderby b.Name descending
//                                       select b.Name;
//            foreach (var item in query)
//            {
//                Console.WriteLine(item);
//            }

            CategoryForm form = new CategoryForm();
            form.ShowDialog();

        }
    }
}
