import { login } from './utils.js';

describe('Caso de prueba: Edición de secretaria', () => {
    it('Edición de secretaria', () => {
        login('miguel', 'miguel');
        cy.get('a[href="list_secretaries.html"]').should('be.visible').click();
        
        cy.get('#tbody tr')        
            .eq(1)                   
            .find('button[data-bs-target="#updateModal"]')          
            .click();                

        cy.get('#updateModal').should('be.visible');
        cy.get('input[id="updNombres"]').should('be.visible').clear().type("Cecylia");
        cy.get('input[id="updApellidoMaterno"]').should('be.visible').type("Torres");
        cy.get('input[id="updTelefono"]').should('be.visible').clear().type("5582092301");
        cy.screenshot('CY11.1');
        cy.get('button[onclick="updateUser()"]').should('be.visible').click();

        cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
        cy.screenshot('CY11.2');
        cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();

        cy.wait(500).screenshot('CY11.3');
    });
});
