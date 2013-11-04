import java.util.Properties;
import java.util.ArrayList;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SecretSanta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean done = false;

		String from = "bacaa14@mail.wlu.edu";

		while (!done) {
			// System.out.println("loop #0");

			done = true;

			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> give = new ArrayList<String>();
			ArrayList<String> recieve = new ArrayList<String>();

			names.add("Alex bacaa14");
			names.add("Andrew seredinskia14");
			names.add("Jeremy mannj14");
			names.add("Ahn trand14");
			names.add("Keith trumpk14");
			names.add("Garrett kollerg14");
			names.add("Dan bocciod14");
			names.add("Nick lehotskyn15");
			names.add("Jimmy yauj16");
			names.add("Mikey bronsteinm15");
			names.add("Blake huddlestonb16");
			names.add("Max stanleym16");
			names.add("TJ fishert15");
			names.add("DJ quijadad15");

			String[][] results = new String[names.size()][2];
			int counter = 0;

			while (names.size() > 1) {
				// System.out.println("loop #1");
				int index = (int) (Math.random() * names.size());
				String name1 = names.get(index);
				names.remove(index);
				index = (int) (Math.random() * names.size());
				String name2 = names.get(index);
				names.remove(index);
				// System.out.println(name1 + " --> " + name2);
				results[counter][0] = name1;
				results[counter][1] = name2;
				counter++;
				if (((name1 == "Garrett" && name2 == "TJ") || (name1 == "TJ" && name2 == "Garrett"))
						|| (name1 == "Keith" && name2 == "Alex")) {
					done = false;
				}
				give.add(name1);
				recieve.add(name2);
			}

			while (give.size() > 0) {
				// System.out.println("loop #2");
				int index = (int) (Math.random() * recieve.size());
				String name1 = recieve.get(index);
				recieve.remove(index);
				index = (int) (Math.random() * give.size());
				String name2 = give.get(index);
				give.remove(index);
				// System.out.println(name1 + " --> " + name2);
				results[counter][0] = name1;
				results[counter][1] = name2;
				counter++;
				if (((name1 == "Garrett" && name2 == "TJ") || (name1 == "TJ" && name2 == "Garrett"))
						|| (name1 == "Keith" && name2 == "Alex")) {
					done = false;
				}
			}

			if (names.size() > 0) {
				System.out.println(names.get(0));
			}

			for (int i = 0; i < results.length; i++) {
				// System.out.println("loop #3");
				String to = results[i][0].substring(results[i][0].indexOf(" "))
						+ "@mail.wlu.edu";
				
				String SSR = results[i][1].substring(0, results[i][1].indexOf(" "));

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

				Session session = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication("bacaa14@gmail.com",
										“*******”);
							}
						});

				try {
					// System.out.println("Hi 3");

					// Create a default MimeMessage object.
					MimeMessage message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(to));

					// Set Subject: header field
					message.setSubject("Secret Santa: Correction (Disregaurd the two previous 'Test' emails)");

					// Now set the actual message
					message.setText("Hi " + results[i][0].substring(0, results[i][0].indexOf(" ")) + ",\n \nSorry, this is the real email. The last one had gramatical errors and the subject was wrong. This automated message is to inform you that this year you're " + SSR + "'s Secret Santa!\n\nWe will decide when we are doing the gift exchange at chapter, but it could be as early as festivus (Two Weeks!) so start thinking!! The spending will be capped at $25.\n\nFraternally,\nBaca \n\nP.S. I am not really testing to see if these autimated messages look good, so I appologize for any weird charicters and such.");

					// Send message
					Transport.send(message);
					System.out.println("Sent message successfully....");
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}

//				System.out
//						.println(results[i][0].substring(0,
//								results[i][0].indexOf(" "))
//								+ " --> "
//								+ results[i][1].substring(0,
//										results[i][1].indexOf(" ")));
			}
		}

	}

}
