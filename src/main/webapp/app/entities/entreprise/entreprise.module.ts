import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
    EntrepriseComponent,
    EntrepriseDetailComponent,
    EntrepriseUpdateComponent,
    EntrepriseDeletePopupComponent,
    EntrepriseDeleteDialogComponent,
    entrepriseRoute,
    entreprisePopupRoute
} from './';

const ENTITY_STATES = [...entrepriseRoute, ...entreprisePopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EntrepriseComponent,
        EntrepriseDetailComponent,
        EntrepriseUpdateComponent,
        EntrepriseDeleteDialogComponent,
        EntrepriseDeletePopupComponent
    ],
    entryComponents: [EntrepriseComponent, EntrepriseUpdateComponent, EntrepriseDeleteDialogComponent, EntrepriseDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEntrepriseModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
