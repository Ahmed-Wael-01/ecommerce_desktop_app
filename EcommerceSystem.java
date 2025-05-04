import java.lang.Math;
import java.util.Scanner;

class Product{
  private int productId;
  private String name;
  private float price;
  public Product(int id, String nm, float p){
	productId = Math.abs(id);
	name = nm;
	price = Math.abs(p);
  }
  
  public int get_id(){
    return productId;
  }
  public void set_id(int id){
    productId = Math.abs(id);
  }

  public float get_price(){
    return price;
  }
  public void set_price(float p){
    price = Math.abs(p);
  }

  public String get_name(){
	  return name;
  }

  public void set_name(String nm){
	name = nm;
  }
}

class ElectronicProduct extends Product{
	private String brand;
	private int warrantyPeriod;
	public ElectronicProduct(int id, String nm, float p, String brnd, int wp){
		super(id, nm, p);
		brand = brnd;
		warrantyPeriod = Math.abs(wp);
	}

	public int get_wp(){
	    return warrantyPeriod;
  	}
  	public void set_wp(int wp){
    		warrantyPeriod = Math.abs(wp);
  	}

  	public String get_brand(){
          	return brand;
  	}

  	public void set_name(String brnd){
        	brand = brnd;
	}

}

class ClothingProduct extends Product{
        private String size;
        private String fabric;
        public ClothingProduct(int id, String nm, float p, String sz, String fc){
                super(id, nm, p);
                size = sz;
                fabric = fc;
        }

        public String get_size(){
                return size;
        }

        public void set_size(String sz){
                size = sz;
        }

	public String get_fabric(){
                return size;
        }

        public void set_fabric(String fc){
                fabric = fc;
        }

}

class BookProduct extends Product{
        private String author;
        private String publisher;
        public BookProduct(int id, String nm, float p, String a, String pub){
                super(id, nm, p);
                author = a;
                publisher = pub;
        }

        public String get_author(){
                return author;
        }

        public void set_author(String a){
                author = a;
        }

        public String get_publisher(){
                return publisher;
        }

        public void set_publisher(String p){
                publisher = p;
        }

}

class Customer{
	private int customerId;
	private String name;
	private String address;

	public Customer(int c_Id, String nm, String add){
		customerId = Math.abs(c_Id);
		name = nm;
		address = add;
	}

	public String get_name(){
                return name;
        }

        public void set_name(String nm){
                name = nm;
        }

        public String get_address(){
                return address;
        }

        public void set_address(String add){
                address = add;
        }

	public int get_id(){
            return customerId;
        }
        public void set_id(int id){
                customerId = Math.abs(id);
        }
}

class Cart{
	private int customerId;
	private int nProducts;
	private Product products[];
	private int pro_indx = 0;

	public Cart(int id, int np, Product[] proArr){
		customerId = Math.abs(id);
		nProducts = Math.abs(np);
		products = proArr;
	}

	public int get_id(){
            return customerId;
        }
        public void set_id(int id){
                customerId = Math.abs(id);
        }

	public int get_np(){
            return nProducts;
        }
        public void set_np(int np){
                nProducts = Math.abs(np);
        }

	public Product[] get_products(){
		return products;
	}

	public void set_products(Product[] new_ps){
		products = new_ps;
	}

	public void addProduct(Product new_p){
		products[pro_indx] = new_p;
		pro_indx++;
	}

	public void removeProduct(){
		pro_indx--;
		products[pro_indx] = null;
	}

	public float calculatePrice(){
		int i;
		float total = 0;
		for (i = 0; i < nProducts; i++){
			total += products[i].get_price();
		}
		return total;
	}

	public void placeOrder(int order_id){
		Order new_order = new Order(customerId, order_id, products, calculatePrice());
		new_order.printOrderInfo();
	}
}

class Order{
	private int customerId;
	private int orderId;
	private Product[] products;
	private float totalPrice;

	public Order(int id, int o_id, Product[] things, float tp){
		customerId = id;
		orderId = o_id;
		products = things;
		totalPrice = tp;
	}

	public void printOrderInfo(){
		int i;
		System.out.println("Order ID: " + orderId + "\nCustomer ID: " + customerId);
		System.out.println("Products:");
		for (i = 0; i < products.length; i++){
			System.out.println(products[i].get_name() + " - $" + products[i].get_price());
		}
		System.out.println("Total price: $" + totalPrice);
	}
}

public class EcommerceSystem{
  public static void main(String[] args){
	  Scanner sc = new Scanner(System.in);
	  ElectronicProduct sp = new ElectronicProduct(1, "smartphone", 599.9f, "Samsung", 1);
	  ClothingProduct cp = new ClothingProduct(2, "T-shirt", 19.99f, "Medium", "Cotton");
	  BookProduct bp = new BookProduct(3, "OOP", 39.99f, "O'Reilly", "X Publications");
	  System.out.println("Welcome to the E-Commerce System!");
	  System.out.println("Please enter year id");
	  int userId = sc.nextInt();
	  sc.nextLine();
	  System.out.println("Please enter your name");
	  String userName = sc.nextLine();
	  System.out.println("Please enter your address");
	  String userAdd = sc.nextLine();

	  Customer user = new Customer(userId, userName, userAdd);

	  System.out.println("How many products you want to add to your cart?");
	  int np = sc.nextInt();

	  Product proArr[] = new Product[np];

	  int op;
	  for (int i = 0; i < np; i++){
		  System.out.println("Which product would you like to add? 1- smartphone 2- T-shirt 3-OOP");
		  op = sc.nextInt();
		  switch(op){
			  case 1:
				  proArr[i] = sp;
				  break;
			  case 2:
				  proArr[i] = cp;
				  break;
			  case 3:
				  proArr[i] = bp;
				  break;
		  }
	  }

	  Cart cc = new Cart(user.get_id(), np, proArr);
	  System.out.println("Your total is $" + cc.calculatePrice() + ". Would you like to place the order? 1- Yes 2- No");
	  op = sc.nextInt();
	  if (op == 1){
	  	cc.placeOrder(1);
	  }
  }
}
