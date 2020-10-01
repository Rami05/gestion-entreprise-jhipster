/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { Compte_bancaireDetailComponent } from 'app/entities/compte-bancaire/compte-bancaire-detail.component';
import { Compte_bancaire } from 'app/shared/model/compte-bancaire.model';

describe('Component Tests', () => {
    describe('Compte_bancaire Management Detail Component', () => {
        let comp: Compte_bancaireDetailComponent;
        let fixture: ComponentFixture<Compte_bancaireDetailComponent>;
        const route = ({ data: of({ compte_bancaire: new Compte_bancaire(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Compte_bancaireDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(Compte_bancaireDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Compte_bancaireDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.compte_bancaire).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
