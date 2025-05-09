package cs3330_group_assignment_5.animal_manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

import javax.swing.JOptionPane;
import com.google.gson.*;

class ShelterController {
    
    private Shelter<Pet> animalShelter;
    private ShelterView shelterDisplay;
    
    public ShelterController(Shelter<Pet> animalShelter, ShelterView shelterDisplay) {
        this.animalShelter = animalShelter;
        this.shelterDisplay = shelterDisplay;
    }

    public void initShelter() {
        Gson jsonParser = new Gson();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        // Load regular pets
        try (InputStream petStream = loader.getResourceAsStream("pets.json")) {
            Reader fileReader = new InputStreamReader(petStream, "UTF-8");
            animalShelter.shelterList = new ArrayList<>(Arrays.asList(jsonParser.fromJson(fileReader, BasicAnimal[].class)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
        // Load exotic animals
        try (InputStream exoticStream = loader.getResourceAsStream("exotic_animals.json")) {
            Reader fileReader = new InputStreamReader(exoticStream, "UTF-8");
            List<ExoticAnimal> exoticAnimals = Arrays.asList(jsonParser.fromJson(fileReader, ExoticAnimal[].class));
            
            for (int i = 0; i < exoticAnimals.size(); i++) {
                AnimalAdapter adaptedAnimal = new AnimalAdapter(exoticAnimals.get(i));
                Pet lastPet = Collections.max(animalShelter.shelterList, new Id());
                adaptedAnimal.setId(lastPet.getId() + 1);
                animalShelter.addPet(adaptedAnimal);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
  
    public void initView() {
        shelterDisplay.initShelterView(animalShelter.shelterList);
        
        // Export to JSON button
        shelterDisplay.writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                String timestamp = currentTime.format(formatter);
                String outputFile = timestamp + "_pets.json";
                
                Gson prettyPrinter = new GsonBuilder().setPrettyPrinting().create();
                try (FileWriter writer = new FileWriter(outputFile)) {
                    for (int i = 0; i < animalShelter.shelterList.size(); i++) {
                        prettyPrinter.toJson(animalShelter.shelterList.get(i), writer);
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
        
        // Sort by name button
        shelterDisplay.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                animalShelter.sortShelterByName();
                refreshDisplayList();
            }
        });
        
        // Sort by age button
        shelterDisplay.ageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                animalShelter.sortShelterByAge();
                refreshDisplayList();
            }
        });
        
        // Sort by species button
        shelterDisplay.specButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                animalShelter.sortShelterBySpecies();
                refreshDisplayList();
            }
        });
        
        // Remove pet button
        shelterDisplay.remButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int removalStatus = 0;
                for (int i = 0; i < animalShelter.shelterList.size(); i++) {
                    Pet currentPet = animalShelter.shelterList.get(i);
                    if (currentPet.getName().compareTo(shelterDisplay.name.getText()) == 0 
                            && currentPet.getType().compareTo(shelterDisplay.type.getText()) == 0 
                            && currentPet.getSpecies().compareTo(shelterDisplay.species.getText()) == 0 
                            && currentPet.getAge() == Integer.parseInt(shelterDisplay.age.getText())) {
                        animalShelter.shelterList.remove(i);
                        removalStatus = 1;
                    }
                }
                
                if (removalStatus != 1) {
                    JOptionPane.showMessageDialog(null, "Item not found");
                }
                refreshDisplayList();
            }
        });
        
        // Adopt pet button
        shelterDisplay.adoptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int selectedIndex = shelterDisplay.list.getSelectedIndex();
                Pet selectedPet = animalShelter.getShelterList().get(selectedIndex);
                
                if (!selectedPet.getAdopted()) {
                    selectedPet.setAdopted(true);
                    shelterDisplay.adoptLabel.setText("Unavailable");
                    shelterDisplay.adoptLabel.setForeground(Color.RED);
                } else {
                    JOptionPane.showMessageDialog(null, "Adopted already");
                }
            }
        });
        
        // Add pet button
        shelterDisplay.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int duplicateCheck = 0;
                
                // Check if pet already exists
                for (int i = 0; i < animalShelter.shelterList.size(); i++) {
                    Pet existingPet = animalShelter.shelterList.get(i);
                    if (existingPet.getName().compareTo(shelterDisplay.name.getText()) == 0 
                            && existingPet.getType().compareTo(shelterDisplay.type.getText()) == 0 
                            && existingPet.getSpecies().compareTo(shelterDisplay.species.getText()) == 0 
                            && existingPet.getAge() == Integer.parseInt(shelterDisplay.age.getText())) {
                        JOptionPane.showMessageDialog(null, "In the shelter");
                        duplicateCheck = 1;
                    }
                }
                
                try {
                    if (duplicateCheck == 0) {
                        if (shelterDisplay.name.getText().matches("[a-zA-Z]+")) {
                            BasicAnimal newAnimal = new BasicAnimal();
                            Pet lastPet = Collections.max(animalShelter.shelterList, new Id());
                            newAnimal.setId(lastPet.getId() + 1);
                            
                            // Capitalize first letter of name
                            String formattedName = shelterDisplay.name.getText().substring(0, 1).toUpperCase() 
                                    + shelterDisplay.name.getText().substring(1);
                            
                            newAnimal.setName(formattedName);
                            newAnimal.setType(shelterDisplay.type.getText());
                            newAnimal.setSpecies(shelterDisplay.species.getText());
                            newAnimal.setAge(Integer.parseInt(shelterDisplay.age.getText()));
                            newAnimal.setAdopted(false);
                            
                            animalShelter.addPet(newAnimal);
                            refreshDisplayList();
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                    "Only alphabetical characters are allowed, and no spaces");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, 
                            "Only alphabetical characters are allowed, and no spaces");
                    ex.printStackTrace();
                }
            }
        });
    }
    
    public void refreshDisplayList() {
        shelterDisplay.updateList(animalShelter.shelterList);
    }
}
