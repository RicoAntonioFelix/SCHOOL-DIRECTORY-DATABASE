/*
 * Copyright (C) 2014-2015 Rico Antonio Felix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Rico Antonio Felix <ricoantoniofelix@yahoo.com>
 */

package com.rico.felix.view;

/*
 * Platform Dependencies
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Platform Dependencies
 */
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/*
 * Local Dependencies
 */
import com.rico.felix.models.Person;
import com.rico.felix.models.Student;
import com.rico.felix.models.Staff;
import com.rico.felix.models.Faculty;

/*
 * Local Dependency
 */
import com.rico.felix.auxiliary.MemberTypes;

/*
 * Local Dependency
 */
import com.rico.felix.data.Directory;

/**
 * This class is used to present dialog boxes for the application's
 * interface.
 */
final class Dialogs
{
    // Dialog box window frame
    private static JFrame dialogBox = new JFrame();

    // Dialog box window frame's panel
    private static JPanel inputPanel = new JPanel();

    // Data for dialog box's title list menu
    private static String[] titles = { "Mr", "Mrs", "Miss" };

    // Dialog box's title list menu
    private static JComboBox<String> titleChooser = new JComboBox<>(titles);

    // Data for dialog box's member senority list menu
    private static String[] classType =
        { "Freshman", "Sophmore", "Junior", "Senior" };

    // Dialog box's member senority list menu
    private static JComboBox<String> classTypeChooser =
        new JComboBox<>(classType);

    // Dialog box labels
    private static JLabel titlesLabel           = new JLabel("Title:");
    private static JLabel firstNameFieldLabel   = new JLabel("First Name:");
    private static JLabel lastNameFieldLabel    = new JLabel("Last Name:");
    private static JLabel emailFieldLabel       = new JLabel("E-mail:");
    private static JLabel classTypeChooserLabel = new JLabel("Class:");
    private static JLabel officeFieldLabel      = new JLabel("Office:");
    private static JLabel tenuredLabel          = new JLabel("Tenured:");

    // Dialog box text input fields
    private static JTextField firstNameField = new JTextField();
    private static JTextField lastNameField  = new JTextField();
    private static JTextField emailField     = new JTextField();
    private static JTextField officeField    = new JTextField();

    // Dialog box radio buttons
    private static ButtonGroup selectionControl  = new ButtonGroup();
    private static JRadioButton isTenuredButton  = new JRadioButton("Yes", true);
    private static JRadioButton notTenuredButton = new JRadioButton("No");

    // Dialog box response buttons
    private static JPanel actionButtonsPanel = new JPanel();
    private static JButton submitButton      = new JButton("Submit");
    private static JButton cancelButton      = new JButton("Cancel");

    // Fields for retrieving dialog box's input data
    private static StringBuilder title           = new StringBuilder("");
    private static StringBuilder firstName       = new StringBuilder("");
    private static StringBuilder lastName        = new StringBuilder("");
    private static StringBuilder email           = new StringBuilder("");
    private static StringBuilder classTypeStatus = new StringBuilder("");
    private static StringBuilder office          = new StringBuilder("");
    private static boolean tenured;

    /*
     * Statically configure dialog box's layout and bind action listeners
     * to the response buttons
     */
    static
    {
        dialogBox.setLayout(new BorderLayout());

        configureInputPanelLayout();

        // Group radio buttons together for exclusive selection semantics
        selectionControl.add(isTenuredButton);
        selectionControl.add(notTenuredButton);

        configureResponseButtonsPanelLayout();

        // Add panels to the dialog box's frame
        dialogBox.getContentPane().add(inputPanel, BorderLayout.CENTER);
        dialogBox.getContentPane().add(actionButtonsPanel, BorderLayout.SOUTH);
        dialogBox.setResizable(false);

        configureSubmitButtonActionListener();
        configureCancelButtonActionListener();
    }

    /*
     * Prevent instantiation
     */
    private Dialogs()
    {}

    /**
     * Method used to configure dialog box's input panel layout
     */
    private static void configureInputPanelLayout()
    {
        inputPanel.setLayout(null);
        inputPanel.add(titlesLabel);
        inputPanel.add(titleChooser);
        inputPanel.add(firstNameFieldLabel);
        inputPanel.add(firstNameField);
        inputPanel.add(lastNameFieldLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(emailFieldLabel);
        inputPanel.add(emailField);
        inputPanel.add(classTypeChooserLabel);
        inputPanel.add(classTypeChooser);
        inputPanel.add(officeFieldLabel);
        inputPanel.add(officeField);
        inputPanel.add(tenuredLabel);
        inputPanel.add(isTenuredButton);
        inputPanel.add(notTenuredButton);
    }

    /**
     * Method used to configure dialog box's response buttons panel layout
     */
    private static void configureResponseButtonsPanelLayout()
    {
        actionButtonsPanel.add(submitButton);
        actionButtonsPanel.add(cancelButton);
    }

    /**
     * Method used to configure dialog box's submit button action listener
     */
    private static void configureSubmitButtonActionListener()
    {
        submitButton.addActionListener(event -> {

            // Get data from title list
            title.replace(0, title.length(), titleChooser.getSelectedItem()
                    .toString());

            // Get data from first name input field
            firstName.replace(0, firstName.length(), firstNameField.getText());

            // Get data from last name input field
            lastName.replace(0, lastName.length(), lastNameField.getText());

            // Get data from email input field
            email.replace(0, email.length(), emailField.getText());

            // Get data from member senority list
            classTypeStatus.replace(0, classTypeStatus.length(),
                    classTypeChooser.getSelectedItem().toString());

            // Get data from office input field
            office.replace(0, office.length(), officeField.getText());

            // Get data from selected radio button
            if (isTenuredButton.isSelected())
                tenured = true;
            else
                tenured = false;

            // Close dialog box
            dialogBox.setVisible(false);

            // Try to commit retrieved data into the database
            commit();
        });
    }

    /**
     * Method used to configure dialog box's cancel button action listener
     */
    private static void configureCancelButtonActionListener()
    {
        cancelButton.addActionListener(event -> {
            dialogBox.setVisible(false);
        });
    }

    /**
     * Method used to configure and retrieve a dialog box for the specified member type
     *
     * @param dialogType Member type to configure a dialog box for
     */
    static JFrame getAddMemberDialog(final MemberTypes dialogType)
    {
        switch (dialogType)
        {
            case STUDENT:
            {
                configureDialogBoxForAddingStudentInformation();
                clearDataFields();
                return dialogBox;
            }
            case STAFF:
            {
                configureDialogBoxForAddingStaffMemberInformation();
                clearDataFields();
                return dialogBox;
            }
            case FACULTY:
            {
                configureDialogBoxForAddingFacultyMemberInformation();
                clearDataFields();
                return dialogBox;
            }
        }

        return null;
    }

    /**
     * Method used to configure a dialog box for adding student information to
     * the database
     */
    private static void configureDialogBoxForAddingStudentInformation()
    {
        dialogBox.setTitle("Enter Student Information");

        // Hide title list menu which is not needed for student information
        titlesLabel.setVisible(false);
        titleChooser.setVisible(false);

        // Hide office input field which is not needed for student information
        officeFieldLabel.setVisible(false);
        officeField.setVisible(false);

        // Hide tenured status selection buttons which is not needed for student
        // information
        tenuredLabel.setVisible(false);
        isTenuredButton.setVisible(false);
        notTenuredButton.setVisible(false);

        emailFieldLabel.setVisible(true);
        emailField.setVisible(true);

        classTypeChooserLabel.setVisible(true);
        classTypeChooser.setVisible(true);

        firstNameFieldLabel.setBounds(5, 10, 75, 20);
        firstNameField.setBounds(80, 5, 300, 29);

        lastNameFieldLabel.setBounds(5, 40, 75, 20);
        lastNameField.setBounds(80, 34, 300, 29);

        emailFieldLabel.setBounds(30, 67, 50, 20);
        emailField.setBounds(80, 62, 300, 29);

        classTypeChooserLabel.setBounds(33, 92, 50, 20);
        classTypeChooser.setBounds(80, 90, 300, 25);

        dialogBox.setSize(400, 180);
        dialogBox.setLocationRelativeTo(View.getFrame());

        clearTextInputFields();
    }

    /**
     * Method used to configure a dialog box for adding staff member information to
     * the database
     */
    private static void configureDialogBoxForAddingStaffMemberInformation()
    {
        dialogBox.setTitle("Enter Staff Member Information");

        // Hide member type senority list menu which is not needed for staff member
        // information
        classTypeChooserLabel.setVisible(false);
        classTypeChooser.setVisible(false);

        // Hide tenured status selection buttons which is not needed for staff member
        // information
        tenuredLabel.setVisible(false);
        isTenuredButton.setVisible(false);
        notTenuredButton.setVisible(false);

        titlesLabel.setVisible(true);
        titleChooser.setVisible(true);

        emailFieldLabel.setVisible(true);
        emailField.setVisible(true);

        officeFieldLabel.setVisible(true);
        officeField.setVisible(true);

        titlesLabel.setBounds(42, 7, 75, 20);
        titleChooser.setBounds(80, 5, 300, 25);

        firstNameFieldLabel.setBounds(5, 40, 75, 20);
        firstNameField.setBounds(80, 34, 300, 29);

        lastNameFieldLabel.setBounds(5, 67, 75, 20);
        lastNameField.setBounds(80, 62, 300, 29);

        emailFieldLabel.setBounds(33, 92, 50, 20);
        emailField.setBounds(80, 90, 300, 29);

        officeFieldLabel.setBounds(37, 125, 80, 20);
        officeField.setBounds(80, 118, 300, 29);

        dialogBox.setSize(400, 220);
        dialogBox.setLocationRelativeTo(View.getFrame());

        clearTextInputFields();
    }

    /**
     * Method used to configure a dialog box for adding faculty member information to
     * the database
     */
    private static void configureDialogBoxForAddingFacultyMemberInformation()
    {
        dialogBox.setTitle("Enter Faculty Member Information");

        // Hide title list menu which is not needed for faculty member information
        titlesLabel.setVisible(false);
        titleChooser.setVisible(false);

        // Hide member type senority list menu which is not needed for faculty member
        // information
        classTypeChooserLabel.setVisible(false);
        classTypeChooser.setVisible(false);

        emailFieldLabel.setVisible(true);
        emailField.setVisible(true);

        officeFieldLabel.setVisible(true);
        officeField.setVisible(true);

        tenuredLabel.setVisible(true);
        isTenuredButton.setVisible(true);
        notTenuredButton.setVisible(true);

        firstNameFieldLabel.setBounds(5, 10, 75, 20);
        firstNameField.setBounds(80, 5, 300, 29);

        lastNameFieldLabel.setBounds(5, 40, 75, 20);
        lastNameField.setBounds(80, 34, 300, 29);

        emailFieldLabel.setBounds(30, 67, 50, 20);
        emailField.setBounds(80, 62, 300, 29);

        officeFieldLabel.setBounds(33, 92, 50, 20);
        officeField.setBounds(80, 90, 300, 29);

        tenuredLabel.setBounds(33, 120, 80, 20);
        isTenuredButton.setBounds(100, 120, 80, 20);
        notTenuredButton.setBounds(185, 120, 80, 20);

        dialogBox.setSize(400, 220);
        dialogBox.setLocationRelativeTo(View.getFrame());

        clearTextInputFields();
    }

    /**
     * Method used to configure and provide a dialog box for deleting a member from
     * within the database
     */
    static JFrame getRemoveMemberDialog()
    {
        dialogBox.setTitle("Remove Member Information");

        // Hide title list menu which is not needed for deleting a record
        titlesLabel.setVisible(false);
        titleChooser.setVisible(false);

        // Hide email input field which is not needed for deleting a record
        emailFieldLabel.setVisible(false);
        emailField.setVisible(false);

        // Hide member type senority list menu which is not needed for deleting a
        // record
        classTypeChooserLabel.setVisible(false);
        classTypeChooser.setVisible(false);

        // Hide office input field which is not needed for deleting a record
        officeFieldLabel.setVisible(false);
        officeField.setVisible(false);

        // Hide tenured status selection buttons which is not needed for deleting a
        // record
        tenuredLabel.setVisible(false);
        isTenuredButton.setVisible(false);
        notTenuredButton.setVisible(false);

        firstNameFieldLabel.setVisible(true);
        firstNameField.setVisible(true);

        lastNameFieldLabel.setVisible(true);
        lastNameField.setVisible(true);

        firstNameFieldLabel.setBounds(5, 10, 75, 20);
        firstNameField.setBounds(80, 5, 300, 29);

        lastNameFieldLabel.setBounds(5, 40, 75, 20);
        lastNameField.setBounds(80, 34, 300, 29);

        dialogBox.setSize(400, 140);
        dialogBox.setLocationRelativeTo(View.getFrame());

        clearTextInputFields();
        clearDataFields();

        return dialogBox;
    }

    /**
     * Method used to configure and provide a dialog box for finding a member
     * within the database
     */
    static JFrame getFindMemberDialog()
    {
        dialogBox.setTitle("Find Member Information");

        // Hide title list menu which is not needed for finding a record
        titlesLabel.setVisible(false);
        titleChooser.setVisible(false);

        // Hide email input field which is not needed for finding a record
        emailFieldLabel.setVisible(false);
        emailField.setVisible(false);

        // Hide member type senority list menu which is not needed for finding a
        // record
        classTypeChooserLabel.setVisible(false);
        classTypeChooser.setVisible(false);

        // Hide office input field which is not needed for finding a record
        officeFieldLabel.setVisible(false);
        officeField.setVisible(false);

        // Hide tenured status selection buttons which is not needed for finding a
        // record
        tenuredLabel.setVisible(false);
        isTenuredButton.setVisible(false);
        notTenuredButton.setVisible(false);

        firstNameFieldLabel.setBounds(5, 10, 75, 20);
        firstNameField.setBounds(80, 5, 300, 29);

        lastNameFieldLabel.setBounds(5, 40, 75, 20);
        lastNameField.setBounds(80, 34, 300, 29);

        dialogBox.setSize(400, 140);
        dialogBox.setLocationRelativeTo(View.getFrame());

        clearTextInputFields();
        clearDataFields();

        return dialogBox;
    }

    /**
     * Method used to clear text input fields
     */
    private static void clearTextInputFields()
    {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        officeField.setText("");
    }

    /**
     * Method used to clear data fields for incoming data
     */
    private static void clearDataFields()
    {
        firstName.replace(0, firstName.length(), "");
        lastName.replace(0, lastName.length(), "");
        email.replace(0, email.length(), "");
        office.replace(0, office.length(), "");
    }

    /**
     * Method used to perform an operation associated with a dialog box's
     * submit button
     */
    private static void commit()
    {
        final String dialogBoxTitle = dialogBox.getTitle();

        if (dialogBoxTitle.equals("Enter Student Information"))
        {
            studentDialogBoxSubmitButtonAction();
        }
        else if (dialogBoxTitle.equals("Enter Staff Member Information"))
        {
            staffMemberDialogBoxSubmitButtonAction();
        }
        else if (dialogBoxTitle.equals("Enter Faculty Member Information"))
        {
            facultyMemberDialogBoxSubmitButtonAction();
        }
        else if (dialogBoxTitle.equals("Remove Member Information"))
        {
            removeMemberDialogBoxSubmitButtonAction();
        }
        else if (dialogBoxTitle.equals("Find Member Information"))
        {
            findMemberDialogBoxSubmitButtonAction();
        }
        System.gc();
    }

    /**
     * Method used to perform the operation associated with add student dialog
     * box's submit button
     */
    private static void studentDialogBoxSubmitButtonAction()
    {
        final String firstNameInput = firstName.toString();
        final String lastNameInput  = lastName.toString();
        final String emailInput     = email.toString();

        if (!firstNameInput.equals("")
            && !lastNameInput.equals("")
            && !emailInput.equals(""))
        {
            Directory.addPerson(
                new Student(firstNameInput, lastNameInput, emailInput,
                            classTypeStatus.toString()));

            announceSuccessFeedback();
        }
        else
        {
            announceFailureFeedback();
        }
    }

    /**
     * Method used to perform the operation associated with add staff member
     * dialog box's submit button
     */
    private static void staffMemberDialogBoxSubmitButtonAction()
    {
        final String firstNameInput = firstName.toString();
        final String lastNameInput  = lastName.toString();
        final String emailInput     = email.toString();
        final String officeInput    = office.toString();

        if (!firstNameInput.equals("")
            && !lastNameInput.equals("")
            && !emailInput.equals("")
            && !officeInput.equals(""))
        {
            Directory.addPerson(
                new Staff(title.toString(), firstNameInput, lastNameInput,
                          emailInput, officeInput));

            announceSuccessFeedback();
        }
        else
        {
            announceFailureFeedback();
        }
    }

    /**
     * Method used to perform the operation associated with add faculty member
     * dialog box's submit button
     */
    private static void facultyMemberDialogBoxSubmitButtonAction()
    {
        final String firstNameInput = firstName.toString();
        final String lastNameInput  = lastName.toString();
        final String emailInput     = email.toString();
        final String officeInput    = office.toString();

        if (!firstNameInput.equals("")
            && !lastNameInput.equals("")
            && !emailInput.equals("")
            && !officeInput.equals(""))
        {
            Directory.addPerson(new Faculty(firstNameInput, lastNameInput,
                                emailInput, tenured, officeInput));

            announceSuccessFeedback();
        }
        else
        {
            announceFailureFeedback();
        }
    }

    /**
     * Method used to perform the operation associated with remove member
     * dialog box's submit button
     */
    private static void removeMemberDialogBoxSubmitButtonAction()
    {
        final String firstNameInput = firstName.toString();
        final String lastNameInput  = lastName.toString();

        if (!firstNameInput.equals("")
            && !lastNameInput.equals(""))
        {
            if (Directory.removePerson(lastNameInput + " " + firstNameInput)
                != null)
            {
                announceRemoveFeedback();
            }
            else
            {
                announceFailureFeedback();
            }
        }
    }

    /**
     * Method used to perform the operation associated with find member
     * dialog box's submit button
     */
    private static void findMemberDialogBoxSubmitButtonAction()
    {
        final String firstNameInput = firstName.toString();
        final String lastNameInput  = lastName.toString();

        if (!firstNameInput.equals("")
            && !lastNameInput.equals(""))
        {
            Person wantedPerson = Directory.retrievePerson(lastNameInput
                                                + " " + firstNameInput);
            if (wantedPerson != null)
            {
                final JTextArea viewDisplay = View.getDisplay();
                
                viewDisplay.setEditable(true);
                viewDisplay.replaceRange(firstNameInput + " " + lastNameInput
                                      + " Information:\n\n" + wantedPerson, 0,
                                      viewDisplay.getText().length());
                viewDisplay.setEditable(false);
            }
            else
            {
                final JTextArea viewDisplay = View.getDisplay();

                viewDisplay.setEditable(true);
                viewDisplay.replaceRange(firstNameInput + " " + lastNameInput
                                         + " cannot be found ", 0,
                                         viewDisplay.getText().length());
                viewDisplay.setEditable(false);
            }
        }
    }

    /**
     * Method used to announce a feedback message if an operation was successful
     */
    private static void announceSuccessFeedback()
    {
        final JTextArea viewDisplay = View.getDisplay();

        viewDisplay.setEditable(true);
        viewDisplay.replaceRange(firstName.toString() + " " + lastName.toString()
                                 + " was added successfully", 0,
                                 viewDisplay.getText().length());
        viewDisplay.setEditable(false);
    }

    /**
     * Method used to announce a feedback message if an operation was unsuccessful
     */
    private static void announceFailureFeedback()
    {
        final JTextArea viewDisplay = View.getDisplay();

        viewDisplay.setEditable(true);
        viewDisplay.replaceRange("Update was unsuccessful", 0,
                                 viewDisplay.getText().length());
        viewDisplay.setEditable(false);
    }

    /**
     * Method used to announce a feedback message if a remove operation was successful
     */
    private static void announceRemoveFeedback()
    {
        final JTextArea viewDisplay = View.getDisplay();

        viewDisplay.setEditable(true);
        viewDisplay.replaceRange(firstName.toString() + " " + lastName.toString()
                                 + " was removed successfully", 0,
                                 viewDisplay.getText().length());
        viewDisplay.setEditable(false);
    }
}
