import { login } from './utils.js';

describe('Caso de prueba: Edición de piso', () => {
    it('Edición de piso', () => {
        login('miguel', 'miguel');
        cy.get('a[href="list_floors.html"]').should('be.visible').click();

        cy.get('#floorCards .card') 
            .contains('.card-title', 'Piso 04') 
            .parents('.card')                 
            .find('button.btn-primary')       
            .click();                        

        cy.get('#updateModal').should('be.visible');
        cy.get('input[id="updNombre"]').should('be.visible').clear().type("PISO 4");
        cy.screenshot('CY12.1');
        cy.get('button[onclick="updateFloor()"]').should('be.visible').click();

        cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
        cy.screenshot('CY12.2');
        cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();

        cy.wait(500).screenshot('CY12.3');
    });
});
