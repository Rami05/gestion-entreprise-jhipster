/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContratComponentsPage, ContratDeleteDialog, ContratUpdatePage } from './contrat.page-object';

const expect = chai.expect;

describe('Contrat e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let contratUpdatePage: ContratUpdatePage;
    let contratComponentsPage: ContratComponentsPage;
    let contratDeleteDialog: ContratDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Contrats', async () => {
        await navBarPage.goToEntity('contrat');
        contratComponentsPage = new ContratComponentsPage();
        await browser.wait(ec.visibilityOf(contratComponentsPage.title), 5000);
        expect(await contratComponentsPage.getTitle()).to.eq('jhipsterApp.contrat.home.title');
    });

    it('should load create Contrat page', async () => {
        await contratComponentsPage.clickOnCreateButton();
        contratUpdatePage = new ContratUpdatePage();
        expect(await contratUpdatePage.getPageTitle()).to.eq('jhipsterApp.contrat.home.createOrEditLabel');
        await contratUpdatePage.cancel();
    });

    it('should create and save Contrats', async () => {
        const nbButtonsBeforeCreate = await contratComponentsPage.countDeleteButtons();

        await contratComponentsPage.clickOnCreateButton();
        await promise.all([contratUpdatePage.setReferenceInput('reference'), contratUpdatePage.employeSelectLastOption()]);
        expect(await contratUpdatePage.getReferenceInput()).to.eq('reference');
        await contratUpdatePage.save();
        expect(await contratUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await contratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Contrat', async () => {
        const nbButtonsBeforeDelete = await contratComponentsPage.countDeleteButtons();
        await contratComponentsPage.clickOnLastDeleteButton();

        contratDeleteDialog = new ContratDeleteDialog();
        expect(await contratDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.contrat.delete.question');
        await contratDeleteDialog.clickOnConfirmButton();

        expect(await contratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
