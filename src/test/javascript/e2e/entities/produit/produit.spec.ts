/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProduitComponentsPage, ProduitDeleteDialog, ProduitUpdatePage } from './produit.page-object';

const expect = chai.expect;

describe('Produit e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let produitUpdatePage: ProduitUpdatePage;
    let produitComponentsPage: ProduitComponentsPage;
    let produitDeleteDialog: ProduitDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Produits', async () => {
        await navBarPage.goToEntity('produit');
        produitComponentsPage = new ProduitComponentsPage();
        await browser.wait(ec.visibilityOf(produitComponentsPage.title), 5000);
        expect(await produitComponentsPage.getTitle()).to.eq('jhipsterApp.produit.home.title');
    });

    it('should load create Produit page', async () => {
        await produitComponentsPage.clickOnCreateButton();
        produitUpdatePage = new ProduitUpdatePage();
        expect(await produitUpdatePage.getPageTitle()).to.eq('jhipsterApp.produit.home.createOrEditLabel');
        await produitUpdatePage.cancel();
    });

    it('should create and save Produits', async () => {
        const nbButtonsBeforeCreate = await produitComponentsPage.countDeleteButtons();

        await produitComponentsPage.clickOnCreateButton();
        await promise.all([produitUpdatePage.setLibelleInput('libelle')]);
        expect(await produitUpdatePage.getLibelleInput()).to.eq('libelle');
        await produitUpdatePage.save();
        expect(await produitUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await produitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Produit', async () => {
        const nbButtonsBeforeDelete = await produitComponentsPage.countDeleteButtons();
        await produitComponentsPage.clickOnLastDeleteButton();

        produitDeleteDialog = new ProduitDeleteDialog();
        expect(await produitDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.produit.delete.question');
        await produitDeleteDialog.clickOnConfirmButton();

        expect(await produitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
