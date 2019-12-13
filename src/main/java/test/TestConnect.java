package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mvc.dao.ActorDao;

public class TestConnect {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/META-INF/spring/app-config.xml");

//		AddressDao dao;
//		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//				"/META-INF/spring/app-config.xml");) {
//			dao = ctx.getBean(AddressDao.class);
//			List<Address> add = dao.findAllBySearch("cianjur");
//			for (Address a : add) {
//				Address newAdd = new Address();
//				newAdd.setAddressId(a.getAddressId());
//				newAdd.setCity(a.getCity());
//
//				System.out.println(newAdd.getAddressId() + " --- " + newAdd.getCity().getCityId() + " --- "
//						+ newAdd.getCity().getCity());
//
//			}
//		}
//
//		String lastID = "U119";
//		String next = nextID(lastID);
//		System.out.println(next);
		
		ActorDao dao = ctx.getBean(ActorDao.class);
		int a = dao.countData();
		System.out.println("count data: " + a/10);

	}

	public static String nextID(String lastID) {
		String nextID = "";
		if (lastID == null) {
			nextID = "U0001";
		} else {
			String[] splitNumber = lastID.split("U");
			String countLastNumber = String.format("%04d", Integer.parseInt(splitNumber[1]) + 1);
			nextID = "U" + countLastNumber;
		}
		return nextID;
	}

}
