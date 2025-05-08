package cs3330_group_assignment_5.animal_manager;

public class App {
  public static void main(String[] args) {
    ShelterView view = new ShelterView();
    Shelter shelter = new Shelter();
    ShelterController controllerTest = new ShelterController(shelter, view);
    controllerTest.initShelter();
    controllerTest.initView();
  }
}
