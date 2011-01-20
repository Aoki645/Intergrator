package inter.integrator.db;

import java.text.MessageFormat;
import java.util.ArrayList;

public class QueryBuilder {

	public String columnBuilder(ArrayList<String> listfild){
		String query = "";

		boolean fildPlacement = false;
		for (String fild : listfild){
			if(fildPlacement){	
				query +="," + fild; 
			}else{
				query += fild ;
				fildPlacement = true;
			}
		}
		return query;
	}


	public String selectBuilder(String column, String table){
		
		return  "select " + column + " from " + table;
	}

	public String insertBuilder(String column, String table){
		   String value = "";
	        String size[] = column.split(",");
	        for (int i = 0; i < size.length; i++) {
	            if (i > 0) {
	                value += "," + "?";
	            } else {
	                value += "?";
	            }
	        }
	        Object[] arguments = {column, value};
	        return MessageFormat.format(" insert into " + table
	                + " ({0}) Values({1})", arguments);
	}

	public static void main(String[] args) {
		QueryBuilder query = new QueryBuilder();
		ArrayList<String> colunas = new ArrayList<String>();
		colunas.add("coluna1");
		colunas.add("coluna2");
		colunas.add("coluna3");
		colunas.add("coluna4");
		colunas.add("coluna5");
		colunas.add("coluna6");
		String col =	query.columnBuilder(colunas);
		System.out.println(query.selectBuilder(col, "test3e"));
		System.out.println(query.insertBuilder(col, "teste dsfg"));
	}
}
