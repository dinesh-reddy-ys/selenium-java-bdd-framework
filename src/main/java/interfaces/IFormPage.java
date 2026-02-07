package interfaces;

public interface IFormPage {
	void enterFirstName(String firstName);
	void enterLastName(String lastName);
	void enterEmail(String email);
	void selectGender(String gender);
	void enterMobileNumber(String mobileNumber);
	void enterDateOfBirth(String dateOfBirth);
	void selectSubjects(String subjects);
	void selectHobbies(String hobbies);
	void uploadPicture(String picturePath);
	void enterCurrentAddress(String currentAddress);
    void selectStateAndCity(String state, String city);	
    void submitForm();

}
