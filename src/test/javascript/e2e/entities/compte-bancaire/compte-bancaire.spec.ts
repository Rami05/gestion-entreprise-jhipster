/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { Compte_bancaireComponentsPage, Compte_bancaireDeleteDialog, Compte_bancaireUpdatePage } from './compte-bancaire.page-object';

const expect = chai.expect;

describe('Compte_bancaire e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let compte_bancaireUpdatePage: Compte_bancaireUpdatePage;
    let compte_bancaireComponentsPage: Compte_bancaireComponentsPage;
    let compte_bancaireDeleteDialog: Compte_bancaireDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Compte_bancaires', async () => {
        await navBarPage.goToEntity('compte-bancaire');
        compte_bancaireComponentsPage = new Compte_bancaireComponentsPage();
        await browser.wait(ec.visibilityOf(compte_bancaireComponentsPage.title), 5000);
        expect(await compte_bancaireComponentsPage.getTitle()).to.eq('jhipsterApp.compte_bancaire.home.title');
    });

    it('should load create Compte_bancaire page', async () => {
        await compte_bancaireComponentsPage.clickOnCreateButton();
        compte_bancaireUpdatePage = new Compte_bancaireUpdatePage();
        expect(await compte_bancaireUpdatePage.getPageTitle()).to.eq('jhipsterApp.compte_bancaire.home.createOrEditLabel');
        await compte_bancaireUpdatePage.cancel();
    });

    it('should create and save Compte_bancaires', async () => {
        const nbButtonsBeforeCreate = await compte_bancaireComponentsPage.countDeleteButtons();

        await compte_bancaireComponentsPage.clickOnCreateButton();
        await promise.all([compte_bancaireUpdatePage.setRibInput('5'), compte_bancaireUpdatePage.setAgenceInput('agence')]);
        expect(await compte_bancaireUpdatePage.getRibInput()).to.eq('5');
        expect(await compte_bancaireUpdatePage.getAgenceInput()).to.eq('agence');
        await compte_bancaireUpdatePage.save();
        expect(await compte_bancaireUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await compte_bancaireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Compte_bancaire', async () => {
        const nbButtonsBeforeDelete = await compte_bancaireComponentsPage.countDeleteButtons();
        await compte_bancaireComponentsPage.clickOnLastDeleteButton();

        compte_bancaireDeleteDialog = new Compte_bancaireDeleteDialog();
        expect(await compte_bancaireDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.compte_bancaire.delete.question');
        await compte_bancaireDeleteDialog.clickOnConfirmButton();

        expect(await compte_bancaireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
