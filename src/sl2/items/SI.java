package sl2.items;

public class SI {

	//
	String store, name, genre, yomi;
	
	int price;
	int id;
	
	long created_at, modified_at;
	
	//{"name", "yomi", "store", "price", "genre"}
	public SI(
				String name, String yomi,String store,  
				int price, String genre) {
		
		this.name = name;
		this.yomi = yomi;
		this.store = store;
		
		this.price = price;
		this.genre = genre;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public SI(
				String name, String yomi,String store,
				int price, String genre, int id) {
		
		this.name = name;
		this.yomi = yomi;
		this.store = store;
		
		this.price = price;
		this.genre = genre;
		
		this.id = id;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public SI(
				String name, String yomi,String store,
				int price, String genre, int id,
				long created_at, long modified_at) {
	
		this.name = name;
		this.yomi = yomi;
		this.store = store;
		
		this.price = price;
		this.genre = genre;
		
		this.id = id;
		
		this.created_at = created_at;
		this.modified_at = modified_at;
	
	}//public ShoppingItem(String store, String name, int price, String genre)

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYomi() {
		return yomi;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
