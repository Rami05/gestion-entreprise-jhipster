import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChauffeur } from 'app/shared/model/chauffeur.model';

type EntityResponseType = HttpResponse<IChauffeur>;
type EntityArrayResponseType = HttpResponse<IChauffeur[]>;

@Injectable({ providedIn: 'root' })
export class ChauffeurService {
    public resourceUrl = SERVER_API_URL + 'api/chauffeurs';

    constructor(protected http: HttpClient) {}

    create(chauffeur: IChauffeur): Observable<EntityResponseType> {
        return this.http.post<IChauffeur>(this.resourceUrl, chauffeur, { observe: 'response' });
    }

    update(chauffeur: IChauffeur): Observable<EntityResponseType> {
        return this.http.put<IChauffeur>(this.resourceUrl, chauffeur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IChauffeur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IChauffeur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
