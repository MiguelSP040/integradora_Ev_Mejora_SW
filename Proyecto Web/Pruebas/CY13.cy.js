import { login } from './utils.js';

describe('Caso de prueba: Registrar cama', () => {
  it('Registrar cama', () => {
    login('rubi', 'rubi');
    cy.get('button[data-bs-target="#registerModal"]').should('be.visible').click();

    cy.get('#registerModal').should('be.visible');
    cy.get('input[id="regNombre"]').should('be.visible').type("P1 Cama05");
    cy.screenshot('CY13.1');
    cy.get('button[onclick="saveBed()"]').should('be.visible').click();

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY13.2');
    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    
    cy.wait(500).screenshot('CY13.3');
  });
});
