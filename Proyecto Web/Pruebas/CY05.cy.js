import { login } from './utils.js';

describe('Caso de prueba: Cerrar sesión', () => {
  it('Cerrar sesión', () => {
    login('rubi', 'rubi');
    cy.get('a[href="secretary_profile.html"]').should('be.visible').click();

    cy.get('input[id="editName"]').should('be.visible').clear().type("Rubi Elena");
    cy.get('input[id="editPhoneNumber"]').should('be.visible').clear().type("7774308523");
    cy.get('button[id="saveButton"]').should('not.be.disabled').click();
    cy.screenshot('CY05.1');
    
    cy.get('button[class="swal2-confirm swal2-styled swal2-default-outline"]').should('be.visible').click();
    cy.wait(500).screenshot('CY05.2');

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    cy.screenshot('CY05.3');
  });
});