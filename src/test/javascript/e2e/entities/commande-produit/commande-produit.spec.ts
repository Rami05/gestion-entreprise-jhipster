/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { COMMANDE_PRODUITComponentsPage, COMMANDE_PRODUITDeleteDialog, COMMANDE_PRODUITUpdatePage } from './commande-produit.page-object';

const expect = chai.expect;

describe('COMMANDE_PRODUIT e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let cOMMANDE_PRODUITUpdatePage: COMMANDE_PRODUITUpdatePage;
    let cOMMANDE_PRODUITComponentsPage: COMMANDE_PRODUITComponentsPage;
    let cOMMANDE_PRODUITDeleteDialog: COMMANDE_PRODUITDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load COMMANDE_PRODUITS', async () => {
        await navBarPage.goToEntity('commande-produit');
        cOMMANDE_PRODUITComponentsPage = new COMMANDE_PRODUITComponentsPage();
        await browser.wait(ec.visibilityOf(cOMMANDE_PRODUITComponentsPage.title), 5000);
        expect(await cOMMANDE_PRODUITComponentsPage.getTitle()).to.eq('jhipsterApp.cOMMANDE_PRODUIT.home.title');
    });

    it('should load create COMMANDE_PRODUIT page', async () => {
        await cOMMANDE_PRODUITComponentsPage.clickOnCreateButton();
        cOMMANDE_PRODUITUpdatePage = new COMMANDE_PRODUITUpdatePage();
        expect(await cOMMANDE_PRODUITUpdatePage.getPageTitle()).to.eq('jhipsterApp.cOMMANDE_PRODUIT.home.createOrEditLabel');
        await cOMMANDE_PRODUITUpdatePage.cancel();
    });

    it('should create and save COMMANDE_PRODUITS', async () => {
        const nbButtonsBeforeCreate = await cOMMANDE_PRODUITComponentsPage.countDeleteButtons();

        await cOMMANDE_PRODUITComponentsPage.clickOnCreateButton();
        await promise.all([
            cOMMANDE_PRODUITUpdatePage.setDateCreationInput('2000-12-31'),
            cOMMANDE_PRODUITUpdatePage.setDateModificationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            cOMMANDE_PRODUITUpdatePage.etatSelectLastOption()
        ]);
        expect(await cOMMANDE_PRODUITUpdatePage.getDateCreationInput()).to.eq('2000-12-31');
        expect(await cOMMANDE_PRODUITUpdatePage.getDateModificationInput()).to.contain('2001-01-01T02:30');
        await cOMMANDE_PRODUITUpdatePage.save();
        expect(await cOMMANDE_PRODUITUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await cOMMANDE_PRODUITComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last COMMANDE_PRODUIT', async () => {
        const nbButtonsBeforeDelete = await cOMMANDE_PRODUITComponentsPage.countDeleteButtons();
        await cOMMANDE_PRODUITComponentsPage.clickOnLastDeleteButton();

        cOMMANDE_PRODUITDeleteDialog = new COMMANDE_PRODUITDeleteDialog();
        expect(await cOMMANDE_PRODUITDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.cOMMANDE_PRODUIT.delete.question');
        await cOMMANDE_PRODUITDeleteDialog.clickOnConfirmButton();

        expect(await cOMMANDE_PRODUITComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
