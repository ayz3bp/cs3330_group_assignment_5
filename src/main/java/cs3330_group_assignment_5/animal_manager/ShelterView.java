package cs3330_group_assignment_5.animal_manager;

import javax.swing.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;

class ShelterView {
    // Sort flag
    int sortMode = 0;
    
    // UI Components
    	JButton speciesSortButton = new JButton("Species Sort");
    	JButton removeButton = new JButton("Remove");
    	JButton ageSortButton = new JButton("Age Sort");
	JButton addButton = new JButton("Add New");
	JButton adoptButton = new JButton("Adopt");
	JButton nameSortButton = new JButton("Name Sort");
	JButton exportButton = new JButton("Write to File");
    
    // Input fields
    	JTextField typeField = new JTextField("");
    	JTextField ageField = new JTextField("");
	JTextField nameField = new JTextField("");
	JTextField speciesField = new JTextField("");
    
    // Status labels
    	JLabel adoptionStatusLabel = new JLabel("");
    
    // List components
	    JList<String> animalList;
	    private JFrame mainWindow = new JFrame("OOP Animal Rescue");
	    private DefaultListModel<String> animalListModel;
    
    /**
     * Initializes the shelter view with the provided list of pets
     * @param shelterList List of pets to display
     */
	
    public void initShelterView(List<Pet> shelterList) {
        // Initialize list model with pet names
        animalListModel = new DefaultListModel<>();
        for (int i = 0; i < shelterList.size(); i++) {
            animalListModel.add(i, shelterList.get(i).getName());
        }
        
        // Create the list component
        animalList = new JList<>(animalListModel);
        animalList.setBounds(50, 50, 200, 300);
        mainWindow.add(animalList);
        
        // Create and position labels
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel typeLabel = new JLabel("Type:");
        JLabel speciesLabel = new JLabel("Species:");
        JLabel titleLabel = new JLabel("Our Family:");

	typeLabel.setBounds(287, 110, 100, 25);
        speciesLabel.setBounds(442, 110, 100, 25);
        titleLabel.setBounds(50, 20, 150, 25);
        titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 20));
        adoptionStatusLabel.setBounds(300, 165, 75, 25);
        nameLabel.setBounds(280, 50, 100, 25);
        ageLabel.setBounds(465, 50, 100, 25);
        
        
        // Position input fields
        nameField.setBounds(325, 50, 100, 25);
        speciesField.setBounds(500, 110, 115, 25);
	ageField.setBounds(500, 50, 25, 25);
        typeField.setBounds(325, 110, 100, 25);
        
        // Add labels to the frame
        mainWindow.add(nameLabel);
        mainWindow.add(ageLabel);
        mainWindow.add(typeLabel);
        mainWindow.add(speciesLabel);
        mainWindow.add(titleLabel);
        mainWindow.add(adoptionStatusLabel);
        
        // Add input fields to the frame
        mainWindow.add(nameField);
	mainWindow.add(speciesField); 
	mainWindow.add(typeField);
        mainWindow.add(ageField);
       
        
        
        // Position buttons
	removeButton.setBounds(500, 225, 150, 25);
        exportButton.setBounds(500, 255, 150, 25);
        nameSortButton.setBounds(50, 355, 200, 30);
        ageSortButton.setBounds(50, 390, 200, 30);
        speciesSortButton.setBounds(50, 425, 200, 30);
        adoptButton.setBounds(500, 165, 150, 25);
        addButton.setBounds(500, 195, 150, 25);
        
        
        /**
         * Displays selected list item in the text fields
         */
        animalList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) { 
                int selectedIndex = animalList.getSelectedIndex();
                
                if (selectedIndex > -1 && selectedIndex < shelterList.size()) {
                    // Get the selected pet
                    Pet selectedPet = shelterList.get(selectedIndex);
                    
                    // Update fields with pet information
                    nameField.setText(selectedPet.getName());
                    ageField.setText(Integer.toString(selectedPet.getAge()));
                    typeField.setText(selectedPet.getType());
                    speciesField.setText(selectedPet.getSpecies());
                    
                    // Update adoption status
                    if (!selectedPet.getAdopted()) {
                        adoptionStatusLabel.setText("Adopt Me!");
                        adoptionStatusLabel.setForeground(Color.GREEN);
                    } else {
                        adoptionStatusLabel.setText("Not Available");
                        adoptionStatusLabel.setForeground(Color.RED);
                    }
                } else {
                    // Clear fields if no valid selection
                    clearFields();
                }
            }
        });
        
        // Add buttons to the frame
	mainWindow.add(addButton);
        mainWindow.add(removeButton);
        mainWindow.add(ageSortButton);
        mainWindow.add(speciesSortButton);
        mainWindow.add(exportButton);
        mainWindow.add(nameSortButton);
        mainWindow.add(adoptButton);
        
        
        // Configure and display the window
        mainWindow.setSize(800, 500);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
    }
    
    /**
     * Clears all input fields and status labels
     */
    private void clearFields() {
	    adoptionStatusLabel.setText("");
	    ageField.setText("");
	    typeField.setText("");
	    speciesField.setText("");
            nameField.setText("");
    }
    
    /**
     * Updates the list display with the current shelter list
     * @param shelterList Updated list of pets
     */
    
	public void updateList(List<Pet> shelterList) {
        animalListModel.clear();
        for (int i = 0; i < shelterList.size(); i++) {
            animalListModel.add(i, shelterList.get(i).getName());
        }
    }
}
