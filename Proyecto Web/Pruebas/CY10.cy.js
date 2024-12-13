import { login } from './utils.js';

describe('Caso de prueba: Edición de enfermera', () => {
    it('Edición de enfermera', () => {
        login('miguel', 'miguel');
        cy.get('a[href="list_nurses.html"]').should('be.visible').click();
        
        cy.get('#tbody tr')        
            .eq(2)                   
            .find('button[data-bs-target="#updateModal"]')          
            .click();                

        cy.get('#updateModal').should('be.visible');
        cy.get('input[id="updApellidoMaterno"]').should('be.visible').type("Zavala");
        cy.get('input[id="updTelefono"]').should('be.visible').clear().type("5570238392");
        cy.screenshot('CY10.1');
        cy.get('button[onclick="updateUser()"]').should('be.visible').click();

        cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
        cy.screenshot('CY10.2');
        cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();

        cy.wait(500).screenshot('CY10.3');
    });
});
