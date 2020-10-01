/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { COMMANDE_PRODUITDeleteDialogComponent } from 'app/entities/commande-produit/commande-produit-delete-dialog.component';
import { COMMANDE_PRODUITService } from 'app/entities/commande-produit/commande-produit.service';

describe('Component Tests', () => {
    describe('COMMANDE_PRODUIT Management Delete Component', () => {
        let comp: COMMANDE_PRODUITDeleteDialogComponent;
        let fixture: ComponentFixture<COMMANDE_PRODUITDeleteDialogComponent>;
        let service: COMMANDE_PRODUITService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [COMMANDE_PRODUITDeleteDialogComponent]
            })
                .overrideTemplate(COMMANDE_PRODUITDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(COMMANDE_PRODUITDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(COMMANDE_PRODUITService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
