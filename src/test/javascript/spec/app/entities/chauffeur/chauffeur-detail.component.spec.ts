/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ChauffeurDetailComponent } from 'app/entities/chauffeur/chauffeur-detail.component';
import { Chauffeur } from 'app/shared/model/chauffeur.model';

describe('Component Tests', () => {
    describe('Chauffeur Management Detail Component', () => {
        let comp: ChauffeurDetailComponent;
        let fixture: ComponentFixture<ChauffeurDetailComponent>;
        const route = ({ data: of({ chauffeur: new Chauffeur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ChauffeurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChauffeurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChauffeurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.chauffeur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
