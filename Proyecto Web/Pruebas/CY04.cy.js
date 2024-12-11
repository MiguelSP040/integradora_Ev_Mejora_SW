import { login } from './utils.js';

describe('Caso de prueba: Cerrar sesión', () => {
  it('Cerrar sesión', () => {
    login('miguel', 'miguel');
    cy.get('button[onclick="logout()"]').should('be.visible').click();
    cy.screenshot('CY04.1');

    cy.get('button[class="swal2-confirm swal2-styled swal2-default-outline"]').should('be.visible').click();
    cy.screenshot('CY04.2');

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY04.2');

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    cy.wait(500);
    cy.screenshot('CY04.3');
  });
});
