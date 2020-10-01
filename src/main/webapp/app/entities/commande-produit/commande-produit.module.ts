import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
    COMMANDE_PRODUITComponent,
    COMMANDE_PRODUITDetailComponent,
    COMMANDE_PRODUITUpdateComponent,
    COMMANDE_PRODUITDeletePopupComponent,
    COMMANDE_PRODUITDeleteDialogComponent,
    cOMMANDE_PRODUITRoute,
    cOMMANDE_PRODUITPopupRoute
} from './';

const ENTITY_STATES = [...cOMMANDE_PRODUITRoute, ...cOMMANDE_PRODUITPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        COMMANDE_PRODUITComponent,
        COMMANDE_PRODUITDetailComponent,
        COMMANDE_PRODUITUpdateComponent,
        COMMANDE_PRODUITDeleteDialogComponent,
        COMMANDE_PRODUITDeletePopupComponent
    ],
    entryComponents: [
        COMMANDE_PRODUITComponent,
        COMMANDE_PRODUITUpdateComponent,
        COMMANDE_PRODUITDeleteDialogComponent,
        COMMANDE_PRODUITDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCOMMANDE_PRODUITModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
