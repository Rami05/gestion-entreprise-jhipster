/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ChauffeurComponentsPage, ChauffeurDeleteDialog, ChauffeurUpdatePage } from './chauffeur.page-object';

const expect = chai.expect;

describe('Chauffeur e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let chauffeurUpdatePage: ChauffeurUpdatePage;
    let chauffeurComponentsPage: ChauffeurComponentsPage;
    let chauffeurDeleteDialog: ChauffeurDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Chauffeurs', async () => {
        await navBarPage.goToEntity('chauffeur');
        chauffeurComponentsPage = new ChauffeurComponentsPage();
        await browser.wait(ec.visibilityOf(chauffeurComponentsPage.title), 5000);
        expect(await chauffeurComponentsPage.getTitle()).to.eq('jhipsterApp.chauffeur.home.title');
    });

    it('should load create Chauffeur page', async () => {
        await chauffeurComponentsPage.clickOnCreateButton();
        chauffeurUpdatePage = new ChauffeurUpdatePage();
        expect(await chauffeurUpdatePage.getPageTitle()).to.eq('jhipsterApp.chauffeur.home.createOrEditLabel');
        await chauffeurUpdatePage.cancel();
    });

    it('should create and save Chauffeurs', async () => {
        const nbButtonsBeforeCreate = await chauffeurComponentsPage.countDeleteButtons();

        await chauffeurComponentsPage.clickOnCreateButton();
        await promise.all([
            chauffeurUpdatePage.setIdentiteInput('identite'),
            chauffeurUpdatePage.setCinInput('cin'),
            chauffeurUpdatePage.permis_de_conduitSelectLastOption()
        ]);
        expect(await chauffeurUpdatePage.getIdentiteInput()).to.eq('identite');
        expect(await chauffeurUpdatePage.getCinInput()).to.eq('cin');
        await chauffeurUpdatePage.save();
        expect(await chauffeurUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await chauffeurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Chauffeur', async () => {
        const nbButtonsBeforeDelete = await chauffeurComponentsPage.countDeleteButtons();
        await chauffeurComponentsPage.clickOnLastDeleteButton();

        chauffeurDeleteDialog = new ChauffeurDeleteDialog();
        expect(await chauffeurDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.chauffeur.delete.question');
        await chauffeurDeleteDialog.clickOnConfirmButton();

        expect(await chauffeurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
