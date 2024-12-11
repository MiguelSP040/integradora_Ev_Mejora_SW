import { login } from './utils.js';

describe('Caso de prueba: Registro de enfermera', () => {
  it('Registro de enfermera', () => {
    login('miguel', 'miguel');
    cy.get('a[href="list_floors.html"]').should('be.visible').click();
    cy.get('button[data-bs-target="#registerModal"]').should('be.visible').click();

    cy.get('#registerModal').should('be.visible');
    cy.get('input[id="regNombre"]').should('be.visible').type("Piso 04");
    cy.get('select[id="regSecretary"]').should('be.visible').select("Ana López Gallardo");
    cy.screenshot('CY09.1');
    cy.get('button[onclick="saveFloor()"]').should('be.visible').click();

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY09.2');
    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    
    cy.wait(500).screenshot('CY09.3');
  });
});
