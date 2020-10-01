/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { Permis_de_conduitDetailComponent } from 'app/entities/permis-de-conduit/permis-de-conduit-detail.component';
import { Permis_de_conduit } from 'app/shared/model/permis-de-conduit.model';

describe('Component Tests', () => {
    describe('Permis_de_conduit Management Detail Component', () => {
        let comp: Permis_de_conduitDetailComponent;
        let fixture: ComponentFixture<Permis_de_conduitDetailComponent>;
        const route = ({ data: of({ permis_de_conduit: new Permis_de_conduit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Permis_de_conduitDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(Permis_de_conduitDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Permis_de_conduitDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.permis_de_conduit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
