/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { COMMANDE_PRODUITDetailComponent } from 'app/entities/commande-produit/commande-produit-detail.component';
import { COMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';

describe('Component Tests', () => {
    describe('COMMANDE_PRODUIT Management Detail Component', () => {
        let comp: COMMANDE_PRODUITDetailComponent;
        let fixture: ComponentFixture<COMMANDE_PRODUITDetailComponent>;
        const route = ({ data: of({ cOMMANDE_PRODUIT: new COMMANDE_PRODUIT(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [COMMANDE_PRODUITDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(COMMANDE_PRODUITDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(COMMANDE_PRODUITDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cOMMANDE_PRODUIT).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
